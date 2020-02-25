/**
 * 6-2.js - a simple JavaScript file that gets loaded with
 * page 6 of Workbook 5 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

import { draggablePoints } from "../libs/dragPoints.js";
import { runCanvas } from "../libs/runCanvas.js";
import { decastle } from "./6-decastlejau.js";

window.onload = function() {
  let canvas = document.getElementById("canvas1");
  if (!(canvas instanceof HTMLCanvasElement))
    throw new Error("Canvas is not HTML Element");
  let context = canvas.getContext("2d");
  let pts = [
    [100, 300],
    [100, 100],
    [300, 100],
    [300, 300]
  ];

  function draw(canvas, t) {
    context.clearRect(0, 0, canvas.width, canvas.height);

    context.save();
    decastle(context, pts, t, 50);
    context.restore();
  }
  runCanvas(canvas, draw, 0, true, 0, 1, 0.02);
  let slider = /** @type {HTMLInputElement} */ (document.getElementById(
    "canvas1-slider"
  ));

  draggablePoints(canvas, pts, function() {
    draw(canvas, Number(slider.value));
  });
};
