/**
 * 1-2.js - a simple JavaScript file that gets loaded with
 * box 2 of page 1 of Workbook 2 (CS559)
 *
 * written by Michael Gleicher, January 2019
 * modified, January 2020
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
     * Box 2: draw a rectangle, two different ways
     */
    /** first with canvas */
    // first we need to get the canvas element
    // and then find the drawing context inside it
    // to understand the next funky comment see http://usejsdoc.org/tags-type.html
    /** @type {HTMLCanvasElement} */
    let canvas = (/** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
    let context = canvas.getContext('2d');

    // now that we have the context, we can use it to issue drawing commands
    // the results appear "immediately"
    context.fillStyle = "#F00";
    context.fillRect(30,30,30,30);

    /** now with SVG  */
    // first we need to get the canvas element
    let svg = document.getElementById("svg1");

    // now we can create a square to add
    // note that in practice, we'd use a library to make this (see https://svgjs.com/ for example)
    // less clunky
    // the next two lines make a square object - but since
    // it's a special object, we need to tell the browser that it is
    // SVG (so we need to declare a name space)
    const svgns = "http://www.w3.org/2000/svg";
    let square = document.createElementNS(svgns, 'rect');
    square.setAttribute("x", "30");
    square.setAttribute("y", "30");
    square.setAttribute("width", "30");
    square.setAttribute("height", "30");
    square.setAttribute("fill", "#F00");
    // now add the square to the svg, like any other DOM element
    svg.appendChild(square);
}