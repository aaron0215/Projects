/**
 * 3-2.js - a simple JavaScript file that gets loaded with
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

window.onload = function () {
    /** @type {HTMLCanvasElement} */
    let canvas = ( /** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
    let context = canvas.getContext('2d');

    let slider = /** @type {HTMLInputElement} */ (document.getElementById("slider1"));

    function sliderChange() {
        let sc = Number(slider.value);
        context.clearRect(0, 0, canvas.width, canvas.height);
        context.save();
        context.fillStyle = "goldenrod";
        context.strokeStyle = "red";
        // @@Snippet:centerscale
        context.translate(40, 40);
        context.scale(sc, sc);
        context.translate(-40, -40);
        context.fillRect(30, 30, 20, 20);
        // @@Snippet:end
        context.beginPath();
        context.moveTo(30, 30);
        context.lineTo(50, 50);
        context.moveTo(50, 30);
        context.lineTo(30, 50);
        context.stroke();
        context.restore();
    }
    slider.value = "1";
    slider.oninput = sliderChange;
    sliderChange();
}