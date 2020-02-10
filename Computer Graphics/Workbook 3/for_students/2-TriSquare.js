/**
 * 2-TriSquare.js - a simple JavaScript file that gets loaded with
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

export function drawTriSquare(context) {
    context.fillStyle = "goldenrod";
    context.fillRect(0, 0, 20, 20);
    context.fillStyle = "red";
    context.beginPath();
    context.moveTo(5, 5);
    context.lineTo(5, 15);
    context.lineTo(15, 10);
    context.fill();
}