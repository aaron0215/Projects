/**
 * 4-utilities.js - a simple JavaScript file that gets loaded with
 * page 4 of Workbook 4 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

import {
    draggablePoints
} from "../libs/dragPoints.js";

const circRadius = 7;

/** Utility function: draw a 10x10 square with markings so we know which
 * way is x and which way is Y
 * don't use rect drawing - since it doesn't always transform correctly
 * @param {CanvasRenderingContext2D} context
 */
export function markedSquare(context) {
    function square(x, y, size, color) {
        context.save();
        context.translate(x, y);
        context.fillStyle = color;
        context.beginPath();
        context.moveTo(0, 0);
        context.lineTo(size, 0);
        context.lineTo(size, size);
        context.lineTo(0, size);
        context.closePath();
        context.fill();
        context.restore();
    }
    square(0, 0, 10, "goldenrod");
    square(1, 1, 2, "red");
    square(7, 7, 2, "green");
    square(7, 1, 2, "white");
}

/**
 * Utility function to draw the draggable points
 * @param {CanvasRenderingContext2D} context
 * @param {number} x
 * @param {number} y
 * @param {number} radius
 * @param {string} color
 */
function circle(context, x, y, radius, color) {
    context.save();
    context.fillStyle = color;
    context.beginPath();
    context.arc(x, y, radius, 0, Math.PI * 2);
    context.fill();
    context.restore();
}

export function setup(id, funct) {
    let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById(id));
    let context = canvas.getContext("2d");
    let pts = [
        [20, 20],
        [50, 50]
    ]; // initial points
    function draw1() {
        context.clearRect(0, 0, canvas.width, canvas.height);
        context.save();
        funct(context, pts[0][0], pts[0][1], pts[1][0], pts[1][1]);
        markedSquare(context);
        context.restore();
        circle(context, pts[0][0], pts[0][1], circRadius, "red");
        circle(context, pts[1][0], pts[1][1], circRadius, "green");
    }
    draggablePoints(canvas, pts, draw1, circRadius);
    draw1();
}