/**
 * 3-3.js - a simple JavaScript file that gets loaded with
 * page 3 of Workbook 3 (CS559).
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

import * as trisquare from "./2-TriSquare.js";

window.onload = function () {
    // the first canvas - which works
    {
        // note that I am just using braces to have a new scope so I can
        // keep my variable names
        let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
        let context = canvas.getContext("2d");
        // scale first and then translate the right amount
        context.scale(0.5, 0.5);
        for (let r = 0; r < 4; r++) {
            for (let c = 0; c < 8; c++) {
                context.save();
                context.translate(c * 40, r * 40);
                trisquare.drawTriSquare(context);
                context.restore();
            }
        }
    }

    // the second Canvas - which the student needs to fix so it looks like the previous one
    {
        // note that I am just using braces to have a new scope so I can
        // keep my variable names
        let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas2"));
        let context = canvas.getContext("2d");
        // scale first and then translate the right amount
        for (let r = 0; r < 4; r++) {
            for (let c = 0; c < 8; c++) {
                context.save();
                context.translate(c * 20, r * 20);
                context.scale(0.5,0.5);
                trisquare.drawTriSquare(context);
                context.restore();
            }
        }
    }
}