/**
 * 2-4.js - a simple JavaScript file that gets loaded with
 * page 2 of Workbook 3 (CS559).
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
    /** @type {HTMLCanvasElement} */
    let canvas = ( /** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
    let context = canvas.getContext('2d');
    let slider = /** @type {HTMLInputElement} */ (document.getElementById("slider1"));
    let text = /** @type {HTMLInputElement} */ (document.getElementById("text1"));

    function sliderChange() {
        let val = slider.value;
        text.value = val;
        context.clearRect(0, 0, canvas.width, canvas.height);
        context.save();
        context.scale(Number(val), Number(val));
        context.fillStyle = "goldenrod";
        context.fillRect(10, 10, 20, 20);
        context.restore();
    }
    slider.oninput = sliderChange;
    slider.value = "1";
    sliderChange();
}