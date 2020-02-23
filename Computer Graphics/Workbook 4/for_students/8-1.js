// @ts-check
/* jshint -W069, esversion:6 */

import { draggablePoints } from "../libs/dragPoints.js";

/**
 * drawing function for box 2
 *
 * Use this UI code!
 **/
window.onload = function() {
  /** @type {HTMLCanvasElement} */
  let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById(
    "canvas1"
  ));

  let thePoints = [
    [100, 100],
    [200, 100],
    [200, 200],
    [100, 200]
  ];

  function draw() {
    /** student does stuff here **/
  }

  draggablePoints(canvas, thePoints, draw);
  draw();
};
