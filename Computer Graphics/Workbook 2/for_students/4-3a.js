/**
 * page4.js - a simple JavaScript file that gets loaded with
 * page 4 of Workbook 2 (CS559)
 *
 * written by Michael Gleicher, January 2019
 * modified by Florian Heimerl, August 2019
 *
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
    /**
     * Part 3 (Interaction without Events) - interaction by polling
     */

    // we'll keep track of a set of "dots"
    let box3dots = [];

    /** @type {HTMLCanvasElement} */
    let canvas3 = (/** @type {HTMLCanvasElement} */ document.getElementById("box3canvas"));
    let context3 = canvas3.getContext('2d');

    // we want to know where the mouse is, but we only find out on movement events!
    // so we'll keep some state
    let mouseX = -10;
    let mouseY = -10;
    // when the mouse moves in the canvas, remember where it moves to
    canvas3.onmousemove = function(event) {
        mouseX = event.clientX;
        mouseY = event.clientY;
        // unfortunately, X,Y is relative to the overall window -
        // we need the X,Y inside the canvas!
        // we know that event.target is a HTMLCanvasElement, so tell typescript
        let box = /** @type {HTMLCanvasElement} */(event.target).getBoundingClientRect();
        mouseX -= box.left;
        mouseY -= box.top;
    };
    // if the mouse moves outside the canvas, give an outside value
    canvas3.onmouseleave = function() {
        mouseX = -10;
        mouseY = -10;
    };

    function box3animate() {
        // clear the canvas
        context3.clearRect(0,0,canvas3.width,canvas3.height);
        // figure out where the mouse is
        // that's handled outside
        // if we're inside the canvas, then we'll make a dot
        if ( (mouseX > 0) && (mouseY > 0) ) {
            box3dots.push({"x":mouseX,"y":mouseY});
        }

        // draw all of the dots
        box3dots.forEach(function(dot){
            context3.fillStyle = "#8888FF88";
            context3.fillRect(dot.x-3,dot.y-3,6,6);
        });
        window.requestAnimationFrame(box3animate);
    }
    box3animate();

    document.getElementById("box3but").onclick = function() {
        box3dots = [];
    };

}
