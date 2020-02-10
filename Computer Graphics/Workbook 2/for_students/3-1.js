// JavaScript file to be filled in by the student for Box 3-1
// we'll give you something to get started...

window.onload = function() {
    // you should start by getting the canvas
    let canvas31 = (/** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
    let context31 = canvas1.getContext("2d");
    // then draw the 4 shapes
    context31.fillStyle = "Beige";
    context31.strokeStyle = "#5F9EA0";
    context31.lineWidth = 5;
    context31.arc(30,100,25,0,2*Math.PI);
    context31.fill();
    context31.stroke();

    context31.fillStyle = "FireBrick";
    context31.strokeStyle = "MediumBlue";
    context31.beginPath();
    context31.moveTo(100,75);
    context31.lineTo(75,125);
    context31.lineTo(125,125);
    context31.closePath();
    context31.fill();
    context31.stroke();

    context31.fillStyle = "#FF4500";
    context31.strokeStyle = "#6A5ACD";
    context31.beginPath();
    context31.arc(175,100,25,Math.PI/2,3/2*Math.PI);
    context31.lineTo(200,75);
    context31.arc(225,100,25,3/2*Math.PI,1/2*Math.PI);
    context31.closePath();
    context31.fill();
    context31.stroke();

    context31.fillStyle = "Plum";
    context31.strokeStyle = "SteelBlue";
    context31.beginPath();
    context31.moveTo(300,75);
    context31.lineTo(275,100);
    context31.lineTo(275,125);
    context31.lineTo(395,125);
    context31.lineTo(395,100);
    context31.lineTo(370,75);
    context31.lineTo(335,100);
    context31.closePath();
    context31.fill();
    context31.stroke();

};