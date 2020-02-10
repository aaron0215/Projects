/**
 * 1-TriSquare.js - a simple JavaScript file that gets loaded with
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

// the "export" tells JavaScript what the outside world can see
/**
 * Draw the triangle and square in the first Canvas
 * 
 * @param {CanvasRenderingContext2D} context 
 */
export function drawTriSquare(context) {
    context.fillStyle = "goldenrod";
    context.fillRect(20, 20, 20, 20);
    context.fillStyle = "red";
    context.beginPath();
    context.moveTo(25, 25);
    context.lineTo(25, 35);
    context.lineTo(35, 30);
    context.fill();
}