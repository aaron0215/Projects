// empty shell for students to do their quadcopter
// exercise

// we do enable typescript type checking - see
// https://graphics.cs.wisc.edu/Courses/559-sp2020/pages/typed-js/
// and
// https://github.com/Microsoft/TypeScript/wiki/Type-Checking-JavaScript-Files
// @ts-check

/* Set options for jshint (my preferred linter)
 * disable the warning about using bracket rather than dot
 * even though dot is better
 * https://stackoverflow.com/questions/13192466/how-to-suppress-variable-is-better-written-in-dot-notation
 */
/* jshint -W069, esversion:6 */

window.onload = function () {
    // somewhere in your program (maybe not here) you'll want a line
    // that looks like:
    let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
    let context = canvas.getContext("2d");

    const wingLength = 50;
    const wingWidth = 5;
    const propLength = 25;
    const propWidth = 5;
    const radius = 5;
    let xvalue = 400;
    let yvalue = 400;
    let controlAngle = 0;
    let turnonlight = 0;

    window.addEventListener('keydown',handleKeyDown);
    function handleKeyDown(event){
        let code = event.keyCode;
        switch(code) {
            case 87: 
                yvalue -= 5*Math.cos(controlAngle); 
                xvalue += 5*Math.sin(controlAngle);
                break; // W
            case 83: 
                yvalue += 5*Math.cos(controlAngle); 
                xvalue -= 5*Math.sin(controlAngle);
                break; // S
            case 65: 
                controlAngle -= Math.PI/10; 
                break; // A
            case 68: 
                controlAngle += Math.PI/10; 
                break; // D
            case 81:
                if(turnonlight == 0){ turnonlight = 1; }
                else {turnonlight = 0};
                console.log(turnonlight);
                break;
        }
    }

    /**
     * @param {CanvasRenderingContext2D} context 
     * @param {number} angle
     */
    function drawPropeller(context,angle){
        context.save()
        context.fillStyle = "dimgrey";
        context.lineWidth = 3;
        context.translate(0,wingLength);
        // Blade
        context.save();
        context.rotate(angle);
        context.beginPath();
        context.arc(-propLength,0,propWidth/2,Math.PI/2,-Math.PI/2);
        context.arc(propLength,0,propWidth/2,-Math.PI/2,Math.PI/2);
        context.closePath();
        context.fill();
        context.stroke();
        context.restore();
        // Shaft
        context.beginPath();
        context.arc(0,0,radius,0,2*Math.PI);
        context.fill();
        context.stroke();
        context.closePath;
        context.restore();
    }

    /**
     * Wings
     * @param {CanvasRenderingContext2D} context 
     * @param {number} angle
     */
    function drawWing(context,angle) {
        context.save();
        context.rotate(Math.PI/4);
        context.save();
        for(let wings = 0; wings < 4; wings++){
            context.beginPath();
            context.rect(-wingWidth/2,0,wingWidth,wingLength);
            context.lineWidth = 3;
            context.stroke();
            context.closePath();
            drawPropeller(context,angle);
            context.rotate(Math.PI / 2);
        }
        context.restore();
        context.restore();
    }

    /**
     * Body
     * @param {CanvasRenderingContext2D} context 
     * @param {number} angle
     */
    function drawBody(context,angle,haslight) {
        drawWing(context,angle);
        context.fillStyle = "darkgray";
        context.lineWidth = 3;
        context.beginPath();
        context.arc(-10,-20,5,-Math.PI,-Math.PI/2);
        context.arc(10,-20,5,3*Math.PI/2,2*Math.PI);
        context.lineTo(15,5);
        context.lineTo(7,20);
        context.arc(5,37,3,0,Math.PI/2);
        context.arc(-5,37.5,3,Math.PI/2,Math.PI);
        context.lineTo(-7,20);
        context.lineTo(-15,5);
        context.fill();
        context.stroke();
        context.closePath();
        context.moveTo(8,17);
        context.lineTo(12,27);
        context.arc(7,42,5,0,Math.PI/2);
        context.arc(-7,42,5,Math.PI/2,Math.PI);
        context.lineTo(-12,27);
        context.lineTo(-8,17);
        context.fill();
        context.stroke();
        context.beginPath();
        if(turnonlight == 1 && haslight == 1){
            for(let layer = 50; layer > 0; layer--){
                context.strokeStyle = "yellow";
                context.moveTo(-layer/2,-30-layer);
                context.lineTo(layer/2,-30-layer);
                context.stroke();
            }
        }
        context.closePath();
    }

    function drawScene() {
        let angle = performance.now() / 100;
        let travelangle = performance.now() / 1000;
        context.clearRect(0, 0, canvas.width, canvas.height);
        context.save();
        context.translate(200,200);
        context.translate(100*Math.cos(travelangle),100*Math.sin(travelangle));
        context.rotate(travelangle+Math.PI); // adjust movement
        drawBody(context,angle,0);
        context.restore();
        context.save();
        context.translate(xvalue,yvalue);
        context.rotate(controlAngle);
        drawBody(context,angle,1);
        context.restore();
        window.requestAnimationFrame(drawScene);
    }
    drawScene();

};