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
  let context = canvas.getContext("2d");

  let thePoints = [
    [100, 100],
    [200, 100],
    [250, 200],
    [200, 300],
    [100, 300],
    [50, 200],
  ];

  function draw() {
    /** student does stuff here **/
    context.clearRect(0, 0, canvas.width, canvas.height);
    for(let i = 0; i < thePoints.length; i++){
      context.beginPath();
      context.arc(thePoints[i][0],thePoints[i][1],5,0,2*Math.PI);
      context.fill();
      context.closePath();
      context.beginPath();
      context.moveTo(thePoints[i][0],thePoints[i][1]);
      if(i == thePoints.length-1){
        context.lineTo(thePoints[0][0],thePoints[0][1]);
      } else{
        context.lineTo(thePoints[i+1][0],thePoints[i+1][1]);
      }
      context.lineWidth = 3;
      context.stroke();
      context.closePath();
    }
  }

  draggablePoints(canvas, thePoints, draw);
  draw();
};
