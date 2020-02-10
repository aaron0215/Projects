/**
 * 3-1.js - a simple JavaScript file that gets loaded with
 * page 3 of Workbook 3 (CS559).
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

import * as trisquare from "./2-TriSquare.js";

window.onload = function () {
    /** @type {HTMLCanvasElement} */
    let canvas1 = ( /** @type {HTMLCanvasElement} */ document.getElementById("canvas1"));
    let context1 = canvas1.getContext('2d');
    trisquare.drawTriSquare(context1);

    /** @type {HTMLCanvasElement} */
    let canvas2 = ( /** @type {HTMLCanvasElement} */ document.getElementById("canvas2"));
    let context2 = canvas2.getContext('2d');
    trisquare.drawTriSquare(context2);

    let sliderS = /** @type {HTMLInputElement} */ (document.getElementById("slider2"));
    let sliderT = /** @type {HTMLInputElement} */ (document.getElementById("slider1"));
    let textS = /** @type {HTMLInputElement} */ (document.getElementById("text1"));
    let textT = /** @type {HTMLInputElement} */ (document.getElementById("text2"));

    function sliderChange() {
        let valS = sliderS.value;
        let valT = sliderT.value;
        textS.value = valT;
        textT.value = valS;
        context1.clearRect(0, 0, canvas1.width, canvas1.height);
        context1.save();
        context1.scale(Number(valS), Number(valS));
        context1.translate(Number(valT), 0);
        trisquare.drawTriSquare(context1);
        context1.restore();

        context2.clearRect(0, 0, canvas2.width, canvas2.height);
        context2.save();
        context2.translate(Number(valT), 0);
        context2.scale(Number(valS), Number(valS));
        trisquare.drawTriSquare(context2);
        context2.restore();

    }
    sliderS.oninput = sliderChange;
    sliderS.value = "2";
    sliderT.oninput = sliderChange;
    sliderT.value = "0";
    sliderChange();
}