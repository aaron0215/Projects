/**
 * 4-1.js - a simple JavaScript file that gets loaded with
 * page 4 of Workbook 3 (CS559).
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
    let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
    let canvasC = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas2"));

    let context = canvas.getContext('2d');
    let contextC = canvasC.getContext('2d');

    let slider = /** @type {HTMLInputElement} */ (document.getElementById("slider1"));
    let text = /** @type {HTMLInputElement} */ (document.getElementById("text1"));

    function sliderChange() {
        let val = slider.value;
        text.value = val;
        // draw the first canvas
        context.clearRect(0, 0, canvas.width, canvas.height);
        context.save();
        context.rotate(Number(val) * Math.PI);
        trisquare.drawTriSquare(context);
        context.restore();
        // draw the second canvas
        contextC.clearRect(0, 0, canvas.width, canvas.height);
        contextC.save();
        contextC.translate(canvas.width / 2, canvas.height / 2);
        // draw axes BEFORE rotating
        contextC.beginPath();
        contextC.lineWidth = 1;
        contextC.strokeStyle = 'black';
        contextC.moveTo(-canvas.width / 2, 0);
        contextC.lineTo(canvas.width / 2, 0);
        contextC.moveTo(0, -canvas.height / 2);
        contextC.lineTo(0, canvas.height / 2);
        contextC.stroke();
        contextC.rotate(Number(val) * Math.PI);
        trisquare.drawTriSquare(contextC);
        contextC.restore();

    }
    slider.oninput = sliderChange;
    sliderChange();
}