/**
 * 1-2.js - a simple JavaScript file that gets loaded with
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
 * Draw the triangle and square at a specific X position
 * 
 * @param {CanvasRenderingContext2D} context 
 * @param {number} xval 
 */
function drawTriSquareParameter(context, xval) {
    context.fillStyle = "goldenrod";
    context.fillRect(20 + xval, 20, 20, 20);
    context.fillStyle = "red";
    context.beginPath();
    context.moveTo(25 + xval, 25);
    context.lineTo(25 + xval, 35);
    context.lineTo(35 + xval, 30);
    context.fill();
}

window.onload = function () {
    /** @type {HTMLCanvasElement} */
    let canvas = ( /** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
    let context = canvas.getContext('2d');


    /** @type {HTMLInputElement} */
    let slider = ( /** @type {HTMLInputElement} */ document.getElementById("slider1"));

    // draw the initial things
    let xval = Number(slider.value);
    // draw the boxes
    drawTriSquareParameter(context, xval);

    /** Set up the callback function to move the squares */
    slider.oninput = function () {
        // clear the canvases
        context.clearRect(0, 0, canvas.width, canvas.height);
        // get the X position and convert to a number
        let xval = Number(slider.value);
        // draw the boxes
        drawTriSquareParameter(context, xval);
    };
}