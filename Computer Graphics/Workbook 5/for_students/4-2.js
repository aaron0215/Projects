/**
 * 4-2.js - a simple JavaScript file that gets loaded with
 * page 4 of Workbook 5 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

import { RunCanvas } from "../libs/runCanvas.js";

window.onload = function() {
  let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById(
    "canvas1"
  ));
  let context = canvas.getContext("2d");

  function draw(canvas, t) {
    context.save();
    context.clearRect(0, 0, canvas.width, canvas.height);

    context.translate(20, 20);

    context.strokeStyle = "black";
    context.lineWidth = 1;
    context.beginPath();
    context.moveTo(0, 0);
    context.lineTo(300, 0);
    context.stroke();

    let [x, y] = [t, 0];
    context.fillStyle = "red";
    context.fillRect(300 * x - 5, y - 5, 10, 10);

    let [x2, y2] = [t * t, 0];
    context.fillStyle = "green";
    context.fillRect(300 * x2 - 5, y2 - 5, 10, 10);
    context.restore();
  }
  let rc = new RunCanvas(canvas, draw);
  rc.setupSlider(0, 1, 0.02);
  rc.setValue(0);
};
