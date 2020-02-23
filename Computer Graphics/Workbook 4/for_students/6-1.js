/**
 * 4-3.js - a simple JavaScript file that gets loaded with
 * page 4 of Workbook 5 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

import { runCanvas } from "../libs/runCanvas.js";
import { decastle } from "./6-decastlejau.js";

window.onload = function() {
  let canvas = document.getElementById("canvas1");
  if (!(canvas instanceof HTMLCanvasElement))
    throw new Error("Canvas is not HTML Element");
  let context = canvas.getContext("2d");
  function draw(canvas, t) {
    context.clearRect(0, 0, canvas.width, canvas.height);

    context.save();
    context.translate(40, 40);
    decastle(
      context,
      [
        [0, 0],
        [0, 120]
      ],
      t,
      2
    );
    context.translate(40, 0);
    decastle(
      context,
      [
        [0, 120],
        [60, 0],
        [120, 120]
      ],
      t,
      20
    );
    context.translate(160, 0);
    decastle(
      context,
      [
        [0, 120],
        [0, 0],
        [120, 0],
        [120, 120]
      ],
      t,
      20
    );
    context.translate(160, 0);
    decastle(
      context,
      [
        [10, 120],
        [0, 40],
        [80, 0],
        [160, 40],
        [150, 120]
      ],
      t,
      20
    );
    context.restore();
  }
  runCanvas(canvas, draw, 0, true, 0, 1, 0.02);
};
