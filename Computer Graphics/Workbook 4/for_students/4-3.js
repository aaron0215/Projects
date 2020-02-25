/**
 * 4-3.js - a simple JavaScript file that gets loaded with
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
 * This should perform some transformation - you can decide how it works
 *
 * @param {CanvasRenderingContext2D} context
 * @param {number} x1
 * @param {number} y1
 * @param {number} x2
 * @param {number} y2
 */
function twoDots(context, x1, y1, x2, y2) {
    let angle = Math.atan2((y2-y1),(x2-x1))-Math.PI/4;
    let length = (Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1))/Math.sqrt(2))/10;
    let a = Math.cos(angle)*length;
    let b = Math.sin(angle)*length;
    let c = -Math.sin(angle)*length;
    let d = Math.cos(angle)*length;
    let e = x1;
    let f = y1;
    context.transform(a, b, c, d, e, f);
}

window.onload = function () {
    utilities.setup("canvas1", twoDots);
}