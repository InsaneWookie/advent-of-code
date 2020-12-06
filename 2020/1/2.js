const fs = require('fs');

values = fs.readFileSync('data.txt').toString().split("\n");

values.forEach((x) => {
    values.forEach((y) => {
        values.forEach((z) => {
            if (parseInt(x) + parseInt(y) + parseInt(z) === 2020) {
                console.log(parseInt(x) * parseInt(y) * parseInt(z))
            }
        })
    })
})