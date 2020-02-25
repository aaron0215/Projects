/**
 * 4-4.js - a simple JavaScript file that gets loaded with
 * page 4 of Workbook 4 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

import * as utilities from "./4-utilities.js";

window.onload = function () {
    let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
    let context = canvas.getContext("2d");
    // Square #1
    context.save();
    // transformation version (student should comment out)
    // context.translate(20, 20);
    // context.scale(4, 4);
    // matrix version (student should replace the numbers)
    context.transform(4, 0, 0, 4, 20, 20);
    //
    utilities.markedSquare(context);
    context.restore();

    // Square #2 - We did this one for you!
    context.save();
    // transformation version (student should comment out)
    // context.translate(20,20);
    // context.scale(4,4);
    // context.translate(15,0);
    // matrix version (student should replace the numbers)
    context.transform(4, 0, 0, 4, 80, 20);
    utilities.markedSquare(context);
    //
    context.restore();

    // Square #3
    context.save();
    // transformation version (student should comment out)
    // context.translate(140, 20);
    // context.scale(4, 4);
    // context.rotate(Math.PI / 2);
    // context.translate(0, -10);
    // matrix version (student should replace the numbers)
    context.transform(4*Math.cos(Math.PI / 2), 4*Math.sin(Math.PI / 2), -4*Math.sin(Math.PI / 2), 4*Math.cos(Math.PI / 2), 180, 20);
    //
    utilities.markedSquare(context);
    context.restore();

    // Square #4
    context.save();
    // transformation version (student should comment out)
    // context.rotate(-Math.PI / 2);
    // context.translate(-60, 200);
    // context.scale(4, 4);
    // matrix version (student should replace the numbers)
    context.transform(4*Math.cos(-Math.PI / 2), 4*Math.sin(-Math.PI / 2), -4*Math.sin(-Math.PI / 2), 4*Math.cos(-Math.PI / 2),200,60);
    //
    utilities.markedSquare(context);
    context.restore();

    // Square #5
    context.save();
    // transformation version (student should comment out)
    // context.rotate(-Math.PI / 2);
    // context.translate(-60, 260);
    // context.scale(4, -4);
    // context.translate(0, -10);
    // matrix version (student should replace the numbers)
    context.transform(4*Math.cos(-Math.PI / 2), 4*Math.sin(-Math.PI / 2), 4*Math.sin(-Math.PI / 2), -4*Math.cos(-Math.PI / 2),300,60);
    //
    utilities.markedSquare(context);
    context.restore();
}