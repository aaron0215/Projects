/**
 * Starter file for 5-1.js - the first exercise of page 5 of Workbook 2
 */

// we do enable typescript type checking - see
// http://graphics.cs.wisc.edu/WP/cs559-sp2019/typed-js/
// and
// https://github.com/Microsoft/TypeScript/wiki/Type-Checking-JavaScript-Files
// @ts-check

/* Set options for jshint (my preferred linter)
 * disable the warning about using bracket rather than dot
 * even though dot is better
 * https://stackoverflow.com/questions/13192466/how-to-suppress-variable-is-better-written-in-dot-notation
 */
/* jshint -W069, esversion:6 */

window.onload = function() {
    // student puts their code here
    /** @type {HTMLCanvasElement} */
    let canvas = (/** @type {HTMLCanvasElement} */ document.getElementById("box1canvas"));
    let context = canvas.getContext('2d');
    let boxcircle = [];
    let mouseX = -10;
    let mouseY = -10;
    let radius = 10;

    canvas.onmousemove = function(event) {
        let box = /** @type {HTMLCanvasElement} */(event.target).getBoundingClientRect();
        mouseX = event.clientX - box.left;
        mouseY = event.clientY - box.top;
    };

    canvas.onmouseleave = function() {
        mouseX = -10;
        mouseY = -10;
    };

    // canvas.onmousedown = function() { clicked=1; };
    // canvas.onmouseup = function() { clicked=0; };

    canvas.onclick = function() {
        if ( (mouseX > 0) && (mouseY > 0) && thereiscircle() == 0) {
            boxcircle.push({"x":mouseX,"y":mouseY, "color":"green", "clicked":0});
        }
        boxcircle.forEach(function(circle){
            if(circle.x >= mouseX-10 && circle.y >= mouseY-10 && circle.x <= mouseX+10 && circle.y <= mouseY+10 && circle.color == "yellow"){
                circle.clicked = 1;
            }
        });
    }

    function thereiscircle() {
        let found = 0;
        boxcircle.forEach(function(circle){
            if(circle.x >= mouseX-10 && circle.y >= mouseY-10 && circle.x <= mouseX+10 && circle.y <= mouseY+10){
                found = 1;
            }
        });
        return found;
    }

    function box1animate() {
        context.clearRect(0,0,canvas.width,canvas.height);
        boxcircle.forEach(function(circle){
            context.beginPath();
            if(circle.clicked == 0){ circle.color ="green"; }
            else {circle.color = "red";}
            if(circle.x >= mouseX-10 && circle.y >= mouseY-10 && circle.x <= mouseX+10 && circle.y <= mouseY+10){
                if(circle.clicked == 1){
                    circle.color = "orange";
                } else{ circle.color = "yellow"; }
            }
            context.fillStyle = circle.color;
            context.arc(circle.x,circle.y,radius,0,2*Math.PI);
            context.fill();
            context.closePath();
        });
        window.requestAnimationFrame(box1animate);
    }
    box1animate();

}
