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

// useful constant for doing SVG
const svgns = "http://www.w3.org/2000/svg";

/** a Helper - make an SVG rectangle
 * the click has type any (rather than [function] because handlers have complex signatures)
 *
 * @param {number} x
 * @param {number} y
 * @param {number} w
 * @param {number} h
 * @param {string} fill
 * @param {any} [click]
 */
function makeSVGrect(x,y,w,h,fill,click) {
    let rect = document.createElementNS(svgns, 'rect');
    rect.setAttribute("x", x.toString());
    rect.setAttribute("y", y.toString());
    rect.setAttribute("width", w.toString());
    rect.setAttribute("height", h.toString());
    rect.setAttribute("fill", fill);
    rect.onclick = click;
    return rect;
}

window.onload = function() {
    /**
     * Box 1 (Animation with Canvas) - animate something moving, and many things not moving
     * in both SVG and Canvas
     */
    // set up the "scene" in svg
    let svg1 = document.getElementById("box1svg");
    for(let c=0; c<4; c++) {
        for(let r=0; r<3; r++) {
            svg1.appendChild(makeSVGrect(30+c*50,20+r*20,30,10,"#888"));
        }
    }
    let svg1rect = makeSVGrect(0,35,20,20,"black");
    svg1.appendChild(svg1rect);

    // for Canvas, our "scene" is defined by the drawing function
    // which redraws everything.
    // we need to know the position of the moving box
    /**
     * draw everythign for Box 1 Canvas
     * @param {number} xpos
     */
    function box1canvDrawAll(xpos) {
        // for real speed, these could be put outside the loop
        /** @type {HTMLCanvasElement} */
        let canvas = (/** @type {HTMLCanvasElement} */ document.getElementById("box1canvas"));
        let context = canvas.getContext('2d');
        // clear the canvas
        context.clearRect(0,0,canvas.width,canvas.height);
        // draw the static rectangles
        context.fillStyle = "#888";
        for(let c=0; c<4; c++) {
            for(let r=0; r<3; r++) {
                context.fillRect(30+c*50,20+r*20,30,10);
            }
        }
        // draw the moving rectangle
        context.fillStyle = "black";
        context.fillRect(xpos,35,20,20);
    }

    // the animation loop - we can have 1 function for both elements
    function box1animate() {
        let xpos = (performance.now()/10 % 260) - 10;

        // with Canvas, we have to redraw EVERYTHING
        box1canvDrawAll(xpos);

        // with SVG, no redraw - just change the box
        svg1rect.setAttribute('x',xpos.toString());

        // schedule the next "loop"
        window.requestAnimationFrame(box1animate);
    }
    box1animate();
}
