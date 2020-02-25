// @ts-check
/* jshint -W069, esversion:6 */

/**
 * drawing function for box 2
 * 
 * draw a picture using curves!
 **/
window.onload = function() {
  /** @type {HTMLCanvasElement} */
  let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
  let context = canvas.getContext("2d");
  context.font = "30px Arial";
  context.fillText("Airpods Max Pro 2021", 40, 50);
  context.beginPath();
  context.moveTo(50, 350);
  context.bezierCurveTo(20, 50, 250, 50, 300, 200);
  context.stroke();
  context.closePath();
  context.beginPath();
  context.moveTo(300, 200);
  context.bezierCurveTo(350, 400, 100, 300, 100, 135);
  context.stroke();
  context.closePath();
  context.beginPath();
  context.moveTo(50, 350);
  context.bezierCurveTo(50, 340, 100, 340, 100, 350);
  context.stroke();
  context.closePath();
  context.beginPath();
  context.moveTo(50, 350);
  context.bezierCurveTo(50, 360, 100, 360, 100, 350);
  context.stroke();
  context.closePath();
  context.beginPath();
  context.moveTo(100, 350);
  context.bezierCurveTo(100, 350, 80, 230, 120, 220);
  context.stroke();
  context.closePath();
  context.beginPath();
  context.ellipse(160,160,15,50,3*Math.PI/4,0,2*Math.PI);
  context.fill();
  context.closePath();
  context.beginPath();
  context.moveTo(300, 190);
  context.bezierCurveTo(250, 210, 220, 240, 180, 290);
  context.stroke();
  context.closePath();
  context.beginPath();
  context.moveTo(300, 190);
  context.bezierCurveTo(350, 230, 320, 380, 180, 290);
  context.fillStyle = "White";
  context.fill();
  context.stroke();
  context.closePath();
  
  
};
