/**
 * 1-6.js - a simple JavaScript file that gets loaded with
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

window.onload = function () {
    /**
     * Your turn! understand my code and fix it!
     * This is mainly about understanding what translate does.
     */
    /** @type {HTMLCanvasElement} */
    let canvas = ( /** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
    let context = canvas.getContext('2d');
    /** @type {HTMLButtonElement} */
    let button = ( /** @type {HTMLButtonElement} */ document.getElementById("button1"));

    /**
     * draw the box with a triangle in it - the jump flag says if the
     * button is pressed (if it is, the triangle should move to the right)
     * 
     * The student should fix this function - without using any negative numbers!
     * 
     * @param {number} jump 
     */
    function draw(jump) {
        context.clearRect(0, 0, canvas.width, canvas.height);
        if (jump) {
            context.save();
            context.translate(20, 0);
            context.fillStyle = "blue";
        } else {
            context.restore();
            context.fillStyle = "red";
        }
        context.beginPath();
        context.moveTo(20, 10);
        context.lineTo(10, 30);
        context.lineTo(30, 30);
        context.fill();
    }
    // draw the initial triangle
    draw(0);

    // now make the button work
    button.onmousedown = function () {
        draw(1);
    };
    button.onmouseup = function () {
        draw(0);
    };
}