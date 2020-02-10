/**
 * 4-2.js - a simple JavaScript file that gets loaded with
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

window.onload = function () {
    let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
    let context = canvas.getContext("2d");

    let slider = /** @type {HTMLInputElement} */ (document.getElementById("slider1"));

    function sliderChange() {
        let val = slider.value;
        let spin = Number(val) * Math.PI;
        // draw the first canvas
        context.clearRect(0, 0, canvas.width, canvas.height);

        context.save();
        context.fillStyle = "red";
        context.rotate(spin);
        context.fillRect(30, 30, 20, 20);
        context.restore();

        context.save();
        context.fillStyle = "blue";
        context.translate(80, 30);
        context.rotate(spin);
        context.translate(-80, -30);
        context.fillRect(80, 30, 20, 20);
        context.restore();

        context.save();
        context.fillStyle = "purple";
        context.translate(140, 40);
        context.rotate(spin);
        context.translate(-140, -40);
        context.fillRect(130, 30, 20, 20);
        context.restore();

        context.save();
        context.fillStyle = "goldenrod";
        context.translate(200, 50);
        context.rotate(spin);
        context.translate(-200, -50);
        context.fillRect(180, 30, 20, 20);
        context.restore();
    }
    slider.oninput = sliderChange;
    slider.value = "0";
    sliderChange();
}