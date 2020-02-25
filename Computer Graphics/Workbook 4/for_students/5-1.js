/**
 * 5-1.js - a simple JavaScript file that gets loaded with
 * page 5 of Workbook 4 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

/**
 * If you want to read up on JavaScript classes, check out your favorite book or...
 * the chapter in the Exploring JS book: http://exploringjs.com/es6/ch_classes.html
 * 
 */
class Boid {
    /**
     * 
     * @param {number} x    - initial X position
     * @param {number} y    - initial Y position
     * @param {number} vx   - initial X velocity
     * @param {number} vy   - initial Y velocity
     * @param {number} bounce  - initial bounce status
     * @param {number} sec   - initial color-change time
     */
    constructor(x, y, vx = 1, vy = 0, bounce, sec) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.bounce = bounce;
        this.sec = sec;
    }
    /**
     * Draw the Boid
     * @param {CanvasRenderingContext2D} context 
     */
    draw(context) {
        context.save();
        context.translate(this.x, this.y);
        // let angle = Math.atan2(this.vy,this.vx);
        // context.rotate(angle);
        // context.fillRect(-10, -10, 20, 20);
        context.beginPath();
        context.arc(0,0,10,0,2*Math.PI);
        if(this.bounce == 1){
            if(this.sec != 0){
                context.fillStyle = "red";
                this.sec--;
            } else {
                this.bounce = 0;
            }
        } else {
            context.fillStyle = "orange";
        }
        context.fill();
        context.closePath();
        context.beginPath();
        context.moveTo(0,0);
        context.lineTo(this.vx*10,this.vy*10);
        // context.moveTo(20,0);
        // context.lineTo(0,10);
        // context.lineTo(0,-10);
        context.closePath();
        context.lineWidth = 3;
        context.fillStyle = "Black";
        context.stroke();
        context.restore();
    }
    /**
     * Perform the "steering" behavior -
     * This function should update the velocity based on the other
     * members of the flock.
     * It is passed the entire flock (an array of Boids) - that includes
     * "this"!
     * Note: dealing with the boundaries does not need to be handled here
     * (in fact it can't be, since there is no awareness of the canvas)
     * *
     * And remember, (vx,vy) should always be a unit vector!
     * @param {Array<Boid>} flock 
     */
    steer(flock) {
        /*
		// Note - this sample behavior is just to help you understand
		// what a steering function might  do
		// all this one does is have things go in circles, rather than
		// straight lines
		// Something this simple would not count for the bonus points:
		// a "real" steering behavior must consider other boids,
		// or at least obstacles.
		
        // a simple steering behavior: 
        // create a rotation matrix that turns by a small amount
        // 2 degrees per time step
        const angle = 2 * Math.PI / 180;
        const s = Math.sin(angle);
        const c = Math.cos(angle);

        let ovx = this.vx;
        let ovy = this.vy;

        this.vx =  ovx * c + ovy * s;
        this.vy = -ovx * s + ovy * c;
        */
       
    //    for(let i = 0; i < flock.length; i++) {
    //        let bix  = flock[i].x;
    //        let biy  = flock[i].y;

    // hit the edge
    if(this.x > 590 || this.x < 10){
        this.vx = -this.vx;
        this.x += this.vx * 3;
        this.bounce = 1;
        this.sec = 5;
    }
    if(this.y > 590 || this.y < 10){
        this.vy = -this.vy;
        this.y += this.vy * 3;
        this.bounce = 1;
        this.sec = 5;
    }
        let bx = this.x;
        let by = this.y;
        let bouncehappened = 0;
           
        for(let i = 0; i < flock.length; i++) {
            let bix = flock[i].x;
            let biy = flock[i].y;
            if(this.x != bix  || this.y != biy){
                let dx = bix - bx;
                let dy = biy - by;
                let d = dx*dx+dy*dy;
                if (d < 400) {
                    bouncehappened = 1;
                    flock[i].vx *= -1;
                    flock[i].vy *= -1;
                    flock[i].x += flock[i].vx*5;
                    flock[i].y += flock[i].vy*5;
                    flock[i].bounce = 1;
                    flock[i].sec = 5;
                }       
            }
        }
        if(bouncehappened == 1){
            this.vx *= -1;
            this.vy *= -1;
            this.x += this.vx*5;
            this.y += this.vy*5;
            this.bounce = 1;
            this.sec = 5;
        }
    }
}

window.onload = function () {
    /** @type Array<Boid> */
    let theBoids = [];

    let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("flock"));
    let context = canvas.getContext("2d");

    let speedSlider = /** @type {HTMLInputElement} */ (document.getElementById("speed"));

    function draw() {
        context.clearRect(0, 0, canvas.width, canvas.height);
        theBoids.forEach(boid => boid.draw(context));
    }

    /**
     * Create some initial boids
     * STUDENT: may want to replace this
     */
    // theBoids.push(new Boid(100, 100));
    // theBoids.push(new Boid(200, 200, -1, 0));
    // theBoids.push(new Boid(300, 300, 0, -1));
    // theBoids.push(new Boid(400, 400, 0, 1));

    /**
     * Handle the buttons
     */
    document.getElementById("add").onclick = function () {
        // Students Fill This In
        for(let count = 0; count < 10; count++){
            let x = Math.random()*580+10;
            let y = Math.random()*580+10;
            let vx = Math.random()*2-1;
            while(vx == 0){
                vx = Math.random()*2-1;
            }
            let vy = Math.sqrt(1-vx*vx);
            theBoids.push(new Boid(x, y, vx, vy, 0, 0));
        }
    };
    document.getElementById("clear").onclick = function () {
        // Student Fill This In
        theBoids.length = 0;
    };


    /**
     * The Actual Execution
     */
    function loop() {
        // change directions
        theBoids.forEach(boid => boid.steer(theBoids));
        // move forward
        let speed = Number(speedSlider.value);
        theBoids.forEach(function (boid) {
            boid.x += boid.vx * speed;
            boid.y += boid.vy * speed;
        });
        // make sure that we stay on the screen
        theBoids.forEach(function (boid) {
            boid.x = boid.x % canvas.width;
            boid.y = boid.y % canvas.height;
            if (boid.x < 0) boid.x += canvas.width;
            if (boid.y < 0) boid.y += canvas.height;
        });
        // now we can draw
        draw();
        // and loop
        window.requestAnimationFrame(loop);

    }
    loop();
};