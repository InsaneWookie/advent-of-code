import * as fs from 'fs';

import _ from "lodash";

const values = fs.readFileSync(__dirname + '/data.txt').toString().split("\n");

console.log(values)