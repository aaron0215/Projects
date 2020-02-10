/**
 * Starter file for 5-2.js - the second exercise of page 5 of Workbook 2
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
    let canvas = (/** @type {HTMLCanvasElement} */ document.getElementById("box2canvas"));
    let context = canvas.getContext('2d');
    let boxcircle = [];
    let particles = [];
    let mouseX = -10;
    let mouseY = -10;
    let gravity = 1;
    let timertotal = 60;
    let timertick = 0;

    canvas.onmousemove = function(event) {
        let box = /** @type {HTMLCanvasElement} */(event.target).getBoundingClientRect();
        mouseX = event.clientX - box.left;
        mouseY = event.clientY - box.top;
    };

    canvas.onmouseleave = function() {
        mouseX = -10;
        mouseY = -10;
    };

    // Create particles from explosion . Each firework explodes to 30 particles
    function addParticles(px,py,max){
        for(let count = 0; count < max; count++){
            let angle = Math.random()*Math.PI*2; // explosion angle
            let speed = Math.random()*10+1; // explosion speed
            let r = 255*Math.random();
            let g = 255*Math.random();
            let b = 255*Math.random();
            particles.push({"px":px, "py":py,"vx":0, "vy":0, "currentX":px, "currentY":py, "angle":angle, "speed":speed, "friction":0.93, "r":r, "g":g, "b":b, "tp":1});
        }
    }

    function addFirework(x,y){
        let startx = Math.floor(Math.random() * Math.floor(canvas.width));
        let vx = (startx-x)/100;
        let vy = (400-y)/100;
        let r = 255*Math.random();
        let g = 255*Math.random();
        let b = 255*Math.random();
        let startC = 'rgb('+r+','+g+','+b+')';
        let radius = Math.random()*10+1;
        boxcircle.push({"x":startx,"y":y, "vx":vx, "vy":vy, "color":startC, "exploded":0, "currentX":startx, "currentY":400, "ex":x, "ey":y, "radius":radius});
        let count = radius*10; // count of particles
        addParticles(x,y,count);
    }

    canvas.onclick = function() {
        if ( (mouseX > 0) && (mouseY > 0) ) {
            addFirework(mouseX,mouseY);
        }
    }

    // Main loop
    function box2animate() {
        context.clearRect(0,0,canvas.width,canvas.height);
        if(timertick >= timertotal){
            let randomX = Math.floor(Math.random() * Math.floor(canvas.width-1)+1);
            let randomY = Math.random()*100+100;
            addFirework(randomX,randomY);
            timertick = 0;
        } else {
            timertick++;
        }
        
        // remove fireworks and particles that have gone off the screen
        // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/filter
        boxcircle = boxcircle.filter(
            // this defines a function using "arrow notation"
            // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/Arrow_functions
            circle => ((circle.currentY>=0)&&(circle.currentX>=0)&&(circle.currentX<=canvas.width)&&(circle.currentY<=canvas.height))
            );

        particles = particles.filter(
            p => ((p.currentY>=0)&&(p.currentX>=0)&&(p.currentX<=canvas.width)&&(p.currentY<=canvas.height)&&(p.tp>0.01))
            );

        boxcircle.forEach(function(circle){
            if(circle.exploded == 0){
                context.beginPath();
                context.fillStyle = circle.color;
                context.arc(circle.currentX,circle.currentY,circle.radius,0,2*Math.PI);
                if(circle.currentY > circle.y){
                    circle.currentX -= circle.vx;
                    circle.currentY -= circle.vy;
                } else {
                    circle.exploded = 1;
                }
                context.fill();
                context.closePath();
            } else { // explode
                particles.forEach(function(pt){
                    if(pt.px==circle.ex && pt.py==circle.ey){
                        pt.vx = Math.cos(pt.angle)*pt.speed;
                        pt.vy = Math.sin(pt.angle)*pt.speed+gravity;
                        pt.speed *= pt.friction;
                        context.beginPath();
                        context.strokeStyle = 'rgba('+pt.r+','+pt.g+','+pt.b+','+pt.tp+')';
                        context.lineWidth = 2;
                        context.moveTo(pt.currentX, pt.currentY);
                        context.lineTo(pt.currentX+pt.vx*2, pt.currentY+pt.vy*3);
                        context.stroke();
                        context.closePath();
                        pt.currentX = pt.currentX+pt.vx;
                        pt.currentY = pt.currentY+pt.vy
                        pt.tp -= 0.01;
                        
                    }
                });
            }
        });
        window.requestAnimationFrame(box2animate);
    }
    box2animate();
}
