/**
 * 4-5.js - a simple JavaScript file that gets loaded with
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
 *
 * ShearX function - student should fill this in.
 *
 * @param {CanvasRenderingContext2D} context
 * @param {number} s
 */
function shearX(context, s) {
    context.transform(1, 0, s, 1, 0, 0);

}

window.onload = function () {
    let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
    let context = canvas.getContext("2d");

    context.save();
    context.translate(20, 20);
    context.scale(4, 4);
    shearX(context, 1);
    utilities.markedSquare(context);
    context.restore();

    context.save();
    context.translate(140, 20);
    context.scale(4, 4);
    shearX(context, -1);
    utilities.markedSquare(context);
    context.restore();

    context.save();
    context.translate(20, 80);
    context.scale(4, 4);
    shearX(context, 2);
    utilities.markedSquare(context);
    context.restore();

    context.save();
    context.translate(220, 80);
    context.scale(4, 4);
    shearX(context, -2);
    utilities.markedSquare(context);
    context.restore();
}