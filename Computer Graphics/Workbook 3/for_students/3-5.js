/**
 * 3-5.js - a simple JavaScript file that gets loaded with
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
    /**
     *
     * @param {HTMLCanvasElement} canvas
     */
    function picture(canvas) {
        let context = canvas.getContext("2d");
        // student should use translate and scale to
        // change the coordinate system to [-100,100] [-100,100]
        context.translate(canvas.width/2,canvas.height/2);// line 1
        context.scale(canvas.width/200,-canvas.height/200);// line 2
        // now I'll draw something...
        context.fillStyle = "lightgray";
        context.beginPath();
        context.moveTo(0, -100);
        context.lineTo(100, 0);
        context.lineTo(0, 100);
        context.lineTo(-100, 0);
        context.fill();
        // draw the plus in the center
        context.strokeStyle = "darkred";
        context.lineWidth = 2;
        context.beginPath();
        const dx = 20;
        context.moveTo(0, -dx);
        context.lineTo(0, dx);
        context.moveTo(-dx, 0);
        context.lineTo(dx, 0);
        context.stroke();
        // draw the T
        context.strokeStyle = 'darkblue';
        context.beginPath();
        context.moveTo(-90, 90);
        context.lineTo(-70, 90);
        context.moveTo(-80, 90);
        context.lineTo(-80, 70);
        context.stroke();
    }

    ["canvas1", "canvas2", "canvas3"].forEach(function (name) {
        picture( /** @type {HTMLCanvasElement} */ (document.getElementById(name)));
    });
}