/**
 * 1-1.js - a simple JavaScript file that gets loaded with
 * page 1 of Workbook 5 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

import { runCanvas } from "../libs/runCanvas.js";
import { functionGallery } from "./1-curves.js";

window.onload = function() {
  let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById(
    "canvas1"
  ));
  let context = canvas.getContext("2d");

  function draw1(canvas, t) {
    let rx, ry;
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.save();
    context.translate(20, 40);
    functionGallery(context, t, 0);
    context.restore();
  }
  runCanvas(canvas, draw1, 0, true, 0, 1, 0.02);
};
