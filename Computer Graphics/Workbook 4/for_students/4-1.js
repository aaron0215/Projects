/**
 * 4-1.js - a simple JavaScript file that gets loaded with
 * page 4 of Workbook 4 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

import * as utilities from "./4-utilities.js";

/**
 * TwoDots - a function for the student to write
 * Notice that it gets the two points and the context as arguments
 * This function should apply a transformation
 *
 * You must write this function using rotate, translate and scale
 *
 * @param {CanvasRenderingContext2D} context
 * @param {number} x1
 * @param {number} y1
 * @param {number} x2
 * @param {number} y2
 */
function twoDots(context, x1, y1, x2, y2) {
    context.translate(x1, y1);
    let scalevalue = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1))/10;
    context.scale(scalevalue, scalevalue);
    context.rotate(Math.atan2((y2-y1),(x2-x1)));
}

window.onload = function () {
    utilities.setup("canvas1", twoDots);
}