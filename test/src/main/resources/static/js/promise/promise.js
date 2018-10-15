// var sleep = function (time) {
//     return new Promise(function (resolve, reject) {
//         setTimeout(function () {
//             resolve();
//         }, time);
//     })
// };
//
// var start = async function () {
//     console.log('start');
//     await sleep(3000);
//     console.log('end');
// };

const sleep = (timeountMS) => new Promise((resolve) => {
    setTimeout(resolve, timeountMS);
});

var start = (async () => {  // 声明即执行的 async 函数表达式
    for (var i = 0; i < 5; i++) {
        await sleep(1000);
        console.log(new Date, i);
    }

    await sleep(1000);
    console.log(new Date, i);
});
