// JavaScript file to be filled in by the student for Box 3-2
// we'll give you something to get started...

window.onload = function() {
    // you should start by getting the canvas
    let canvas32 = (/** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
    let context32 = canvas1.getContext("2d");
    // then draw whatever you want!

    // Head
    context32.fillStyle = "#3366ff";
    context32.arc(250,250,150,0,2*Math.PI);
    context32.fill();
    context32.stroke();

    // Face
    context32.beginPath();
    context32.fillStyle = "White";
    context32.arc(250,270,125,0,2*Math.PI);
    context32.fill();
    context32.closePath();

    // Eyes
    context32.beginPath();
    context32.fillStyle = "#f2f2f2";
    context32.ellipse(215,160,30,35,0, 0, 2 * Math.PI);
    context32.fill();
    context32.closePath();

    context32.beginPath();
    context32.fillStyle = "Black";
    context32.lineWidth = 5;
    context32.arc(220,160,10,Math.PI,0);
    context32.stroke();
    context32.closePath();

    context32.beginPath();
    context32.fillStyle = "#f2f2f2";
    context32.ellipse(285,160,30,35,0, 0, 2 * Math.PI);
    context32.fill();
    context32.closePath();

    context32.beginPath();
    context32.lineWidth = 5;
    context32.moveTo(290,147);
    context32.lineTo(270,155);
    context32.lineTo(290,167);
    context32.stroke();
    context32.closePath();

    // Nose
    context32.beginPath();
    context32.fillStyle = "#cc0000";
    context32.arc(252,210,20,2*Math.PI,0);
    context32.fill();
    context32.closePath();
    
    // Mustache
    context32.beginPath();
    context32.strokeStyle = "Black";
    context32.lineWidth = 3;
    context32.moveTo(220,210);
    context32.lineTo(170,200);
    context32.stroke();

    context32.lineWidth = 3;
    context32.moveTo(220,222);
    context32.lineTo(165,222);
    context32.stroke();

    context32.lineWidth = 3;
    context32.moveTo(220,234);
    context32.lineTo(170,248);
    context32.stroke();

    context32.lineWidth = 3;
    context32.moveTo(280,210);
    context32.lineTo(330,200);
    context32.stroke();

    context32.lineWidth = 3;
    context32.moveTo(280,222);
    context32.lineTo(335,222);
    context32.stroke();

    context32.lineWidth = 3;
    context32.moveTo(280,234);
    context32.lineTo(330,248);
    context32.stroke();
    context32.closePath();

    // Mouth
    context32.beginPath();
    context32.lineWidth = 2;
    context32.strokeStyle = "Black";
    context32.moveTo(252,230);
    context32.lineTo(252,255);
    context32.stroke();

    context32.fillStyle = "#e60000";
    context32.moveTo(160,280);
    context32.quadraticCurveTo(252,230,340,280)
    context32.fill();
    context32.closePath();

    context32.beginPath();
    context32.fillStyle = "#cc0000";
    context32.moveTo(160,280);
    context32.bezierCurveTo(192,370,320,370,340,280);
    context32.fill();
    context32.closePath();

    context32.beginPath();
    context32.fillStyle = "#ff704d";
    context32.moveTo(175,310);
    context32.quadraticCurveTo(252,280,328,310);
    context32.fill();
    context32.closePath();

    context32.beginPath();
    context32.fillStyle = "#ff704d";
    context32.moveTo(175,310);
    context32.quadraticCurveTo(252,388,328,310);
    context32.fill();
    context32.closePath();

    // Necklace
    context32.beginPath();
    context32.fillStyle = "#cc0000";
    context32.arc(180,390,10,Math.PI/2,3/2*Math.PI);
    context32.lineTo(340,380);
    context32.arc(320,390,10,3/2*Math.PI,1/2*Math.PI);
    context32.fill();
    context32.closePath();

    context32.beginPath();
    context32.fillStyle = "#ff6600";
    context32.arc(255,405,20,0,2*Math.PI);
    context32.fill();
    context32.closePath();

    context32.beginPath();
    context32.fillStyle = "#ff6600";
    context32.strokeStyle = "#b34700";
    context32.lineWidth = 1;
    context32.arc(237,402,5,Math.PI/2,3/2*Math.PI);
    context32.lineTo(273,397);
    context32.arc(273,402,5,3/2*Math.PI,1/2*Math.PI);
    context32.closePath();
    context32.fill();
    context32.stroke();
    context32.closePath();

    context32.beginPath();
    context32.moveTo(255,425);
    context32.lineTo(255,415);
    context32.stroke();
    context32.closePath();

    context32.beginPath();
    context32.fillStyle = "#b34700";
    context32.arc(255,415,3,0,2*Math.PI);
    context32.fill();
    context32.closePath();

    //Shadow

    for(let i=0; i<=10; i++) {
        context32.fillStyle = `rgba(0,0,0,${(i)/100.0})`;
        console.log(i/20);
        context32.beginPath();
        context32.ellipse(255,460,50-i*2,30-i*2.5,0, 0, 2 * Math.PI);
        context32.fill();
        context32.closePath();
    }


};