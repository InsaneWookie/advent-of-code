"use strict";
exports.__esModule = true;
var fs = require("fs");
var values = fs.readFileSync('data.txt').toString().split("\n");
console.log(values);
