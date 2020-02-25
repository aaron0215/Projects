// @ts-check
/* jshint -W069, esversion:6 */

/**
 * drawing function for box 1
 *
 * draw the spiral - account for the checkbox and slider
 **/
    

window.onload = function() {
    let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
    /** @type{HTMLInputElement} */ let slider = (/** @type{HTMLInputElement} */ document.getElementById("slider"));
    /** @type{HTMLInputElement} */ let checkbox = (/** @type{HTMLInputElement} */ document.getElementById("checkbox"));
    let context = canvas.getContext("2d");

    function draw() {
        context.clearRect(0, 0, canvas.width, canvas.height);
        let conntected = checkbox.checked ? 1 : 0;
        let numDocs = Number(slider.value);
        context.moveTo(200,200);
        let u = 0;
        let previousX = 200;
        let previousY = 200;
        for(let i = 0; i < numDocs; i++){
            context.save();
            context.beginPath();
            let[x,y] = [200+u*180*Math.cos(2*Math.PI*4*u),200+u*180*Math.sin(2*Math.PI*4*u)];
            context.translate(x,y);
            context.fillStyle = "Blue";
            context.arc(0,0,1,0,Math.PI*2);
            context.fill();
            context.closePath();
            context.restore();
            if(conntected == 1){
                context.moveTo(x,y);
                context.lineTo(previousX,previousY);
                context.strokeStyle = "Blue";
                context.stroke();
                previousX = x;
                previousY = y;
            }
            u += 1/numDocs;
        }
        window.requestAnimationFrame(draw);
    }
    draw();
};
