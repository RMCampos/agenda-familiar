const fs = require('fs');
const gulp = require('gulp');
const gulpif = require('gulp-if');
const concat = require('gulp-concat');
const header = require('gulp-header');
const cleanCSS = require('gulp-clean-css');
const sourcemaps = require('gulp-sourcemaps');
const uglifyes = require('uglify-es');
const uglifycss = require('gulp-uglifycss');
const rename = require('gulp-rename');
const maps = require('gulp-sourcemaps');
const composer = require('gulp-uglify/composer');
const uglify = composer(uglifyes, console);
const stripCssComments = require('gulp-strip-css-comments');
const clean = require('gulp-dest-clean');
const imagemin = require('gulp-imagemin');

const argv = require('minimist')(process.argv.slice(2));

const config = {
    production: !!argv.production,
};

console.log('Config ', config);

function addBOM() {
    return header('\ufeff');
};

gulp.task('deploy-css', function() {
    return gulp
        .src('css/app.css')
        .pipe(gulpif(!config.production, sourcemaps.init()))
        .pipe(concat('app.css'))
        .pipe(gulpif(config.production, cleanCSS({ level: 2 })))
        .pipe(gulpif(config.production, stripCssComments({ preserve: false })))
        .pipe(addBOM())
        .pipe(gulpif(!config.production, sourcemaps.write('./')))
        .pipe(gulp.dest('dist/css/'));

});

gulp.task('deploy-image', function() {
    return gulp
        .src('img/*')
        .pipe(clean('dist/img/'))
        .pipe(imagemin())
        .pipe(gulp.dest('dist/img/'));
});

gulp.task('deploy-js', function() {
    return gulp.src('js/*.js')
        .pipe(concat('app.js'))
        .pipe(gulpif(!config.production, uglify()))
        .pipe(addBOM())
        .pipe(gulp.dest('dist/js/'));
});

gulp.task('default', gulp.series('deploy-css', 'deploy-image', 'deploy-js'));
