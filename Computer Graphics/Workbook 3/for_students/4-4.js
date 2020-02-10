/**
 * 4-4.js - a simple JavaScript file that gets loaded with
 * page 4 of Workbook 3 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

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

// length of bones
const wristLength = 20;
const upperArmLength = 20;

// angles for joints
// having these as "global" (to the module) variables is easy
// but generally not a good idea - it means that all arms use
// the same angles
let wristAngle = 0;
let elbowAngle = 0;
let shoulderAngle = 0;

/********
 * Functions that draw the different parts
 * 
 * Each part is in its own local coodinate system
 * Each part draws its children
 */
/**
 * draws the hand - pointing along the X axis, center is where it
 * connects to the wrist (the "wrist joint")
 * @param {CanvasRenderingContext2D} context 
 */
function drawHand(context) {
    context.save();
    context.fillStyle = "lightpink";
    context.beginPath();
    context.moveTo(10, 0);
    context.lineTo(8, -6);
    context.lineTo(0, -4);
    context.lineTo(0, 4);
    context.lineTo(8, 6);
    context.fill();
    context.restore();
}

/**
 * draws the forearm/wrist - the hand gets translated and rotated into place
 * the center of the coordinate system is the elbow joint
 * @param {CanvasRenderingContext2D} context 
 */
function drawWrist(context) {
    context.save();
    // the "wrist bone"
    context.fillStyle = "red";
    context.fillRect(0, -3, wristLength, 6);
    // now place the hand at the "wrist joint"
    context.translate(wristLength, 0);
    context.rotate(wristAngle);
    drawHand(context);
    context.restore();
}

/**
 * draws the upper arm - the forarm gets connected at the elbow
 * the center of the upper arm coordinate system is the shoulder
 * the bone goes along the X axis
 * @param {CanvasRenderingContext2D} context 
 */
function drawUpperArm(context) {
    context.save();
    // draw the "upper arm bone"
    context.fillStyle = "purple";
    context.fillRect(0, -4, upperArmLength, 8);
    context.translate(upperArmLength, 0);
    context.rotate(elbowAngle);
    drawWrist(context);
    context.restore();
}

function drawTorso(context) {
    context.save();
    context.fillStyle = "lightblue";
    context.beginPath();
    context.moveTo(-8, 0);
    context.lineTo(8, 0);
    context.lineTo(12, -20);
    context.lineTo(-12, -20);
    context.fill();
    context.translate(10, -16);
    context.rotate(shoulderAngle);
    drawUpperArm(context);
    context.restore();
}

/**
 * Draw a part in its own coordinate system
 * 
 * This is useful for the demo where we have multiple Canvas
 * so we can show each piece by itself
 * 
 * @param {string} element 
 * @param {function} func 
 */
function drawPart(element, func) {
    let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById(element));
    let context = canvas.getContext("2d");
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.save();
    context.translate(20, canvas.height / 2);
    context.scale(2, 2);
    func(context);
    context.restore();
    context.save();
    context.lineWidth = 1;
    context.strokeStyle = "black";
    context.setLineDash([2, 2]);
    context.beginPath();
    context.moveTo(20, 0);
    context.lineTo(20, canvas.height);
    context.moveTo(0, canvas.height / 2);
    context.lineTo(canvas.width, canvas.height / 2);
    context.stroke();
    context.restore();
}

window.onload = function () {
    let slider1 = /** @type {HTMLInputElement} */ (document.getElementById("slider1"));
    let slider2 = /** @type {HTMLInputElement} */ (document.getElementById("slider2"));
    let slider3 = /** @type {HTMLInputElement} */ (document.getElementById("slider3"));
    slider1.value = "0";
    slider2.value = "0";
    slider3.value = "0";

    function sliderChange() {
        wristAngle = Number(slider1.value) * Math.PI;
        elbowAngle = Number(slider2.value) * Math.PI;
        shoulderAngle = Number(slider3.value) * Math.PI;
        drawPart("canvas1", drawHand);
        drawPart("canvas2", drawWrist);
        drawPart("canvas3", drawUpperArm);
        drawPart("canvas4", drawTorso);
    }

    slider1.oninput = sliderChange;
    slider2.oninput = sliderChange;
    slider3.oninput = sliderChange;

    sliderChange();
}