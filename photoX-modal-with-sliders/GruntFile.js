'use strict';

module.exports = function (grunt) {
    grunt.initConfig({
        clean: ['bower_components'],

        copy: {
            main: {
                files: [
                    //J A V A S C R I P T
                    {
                        "expand": true,
                        "cwd": 'bower_components/angular/',
                        "src": 'angular.min.js',
                        "dest": 'src/main/webapp/resources/build/javascript/',
                        "filter": 'isFile'
                    },
                    {
                        "expand": true,
                        "cwd": 'bower_components/angular-bootstrap/',
                        "src": 'ui-bootstrap-tpls.min.js',
                        "dest": 'src/main/webapp/resources/build/javascript/',
                        "filter": 'isFile'
                    },
                    {
                        "expand": true,
                        "cwd": 'bower_components/jquery/dist/',
                        "src": 'jquery.min.js',
                        "dest": 'src/main/webapp/resources/build/javascript/',
                        "filter": 'isFile'
                    },
                    {
                        "expand": true,
                        "cwd": 'bower_components/bootstrap/dist/js/',
                        "src": 'bootstrap.min.js',
                        "dest": 'src/main/webapp/resources/build/javascript/',
                        "filter": 'isFile'
                    },
                    {
                        "expand": true,
                        "cwd": 'bower_components/jquery-ui/',
                        "src": 'jquery-ui.min.js',
                        "dest": 'src/main/webapp/resources/build/javascript/',
                        "filter": 'isFile'
                    },
                    //C S S
                    {
                        "expand": true,
                        "cwd": 'bower_components/bootstrap/dist/css/',
                        "src": 'bootstrap.min.css',
                        "dest": 'src/main/webapp/resources/build/css',
                        "filter": 'isFile'
                    },
                    {
                        "expand": true,
                        "cwd": 'bower_components/bootstrap/dist/css/',
                        "src": 'bootstrap-theme.min.css',
                        "dest": 'src/main/webapp/resources/build/css',
                        "filter": 'isFile'
                    },
                    {
                        "expand": true,
                        "cwd": 'bower_components/jquery-ui/themes/smoothness/',
                        "src": 'jquery-ui.css',
                        "dest": 'src/main/webapp/resources/build/css',
                        "filter": 'isFile'
                    },
                    {
                        "expand": true,
                        "cwd": 'bower_components/jquery-ui/themes/smoothness/',
                        "src": 'jquery-ui.min.css',
                        "dest": 'src/main/webapp/resources/build/css',
                        "filter": 'isFile'
                    },
                    {
                        "expand": true,
                        "cwd": 'bower_components/jquery-ui/themes/smoothness/images/',
                        "src": '*.*',
                        "dest": 'src/main/webapp/resources/build/css/images',
                        "filter": 'isFile'
                    },
                    //M A P
                    {
                        "expand": true,
                        "cwd": 'bower_components/bootstrap/dist/css/',
                        "src": 'bootstrap.min.css.map',
                        "dest": 'src/main/webapp/resources/build/css',
                        "filter": 'isFile'
                    }
                ]
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-copy');

    grunt.registerTask('build',['copy','clean']);
    grunt.registerTask('default', ['build']);
};