const fs = require('fs');

values = fs.readFileSync('data.txt').toString().split("\n");

values.forEach((x) => {
    values.forEach((y) => {
            if (parseInt(x) + parseInt(y) === 2020) {
                console.log(parseInt(x) * parseInt(y))
            }
    })
})