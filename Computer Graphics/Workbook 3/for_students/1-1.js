/**
 * 1-1.js - a simple JavaScript file that gets loaded with
 * page 1 of Workbook 3 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// we do enable typescript type checking - see
// https://graphics.cs.wisc.edu/Courses/559-sp2020/pages/typed-js/
// and
// https://github.com/Microsoft/TypeScript/wiki/Type-Checking-JavaScript-Files
// @ts-check

/* Set options for jshint (my preferred linter)
 * disable the warning about using bracket rather than dot
 * even though dot is better
 * https://stackoverflow.com/questions/13192466/how-to-suppress-variable-is-better-written-in-dot-notation
 */
/* jshint -W069, esversion:6 */

/**
 * 
 * This is for drawTriSquare - it loads in a separate module that holds the code for the function
 * so we can put that code into a separate file (called "1-TriSquare.js")
 * 
 * Now is a good time to learn about modules!
 * 
 * Check your favorite JavaScript book (if it is up to date with ES6).
 * https://github.com/nzakas/understandinges6/blob/master/manuscript/13-Modules.md
 * is a nice tutorial.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/import
 * is an official reference
 */
import * as trisquare from "./1-TriSquare.js";

window.onload = function () {
    /** @type {HTMLCanvasElement} */
    let canvas = ( /** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
    let context = canvas.getContext('2d');
    trisquare.drawTriSquare(context);
}