// const http = require("http");
// const fs = require("fs");
// const host = 'localhost';
// const port = 8000;
//
// // const requestHtml = function (req, res) {
// //     res.writeHead(200);
// //     res.end("My first server!");
// // };
//
// // const server = http.createServer(requestListener);
// // server.listen(port, host, () => {
// //     console.log(`Server is running on http://${host}:${port}`);
// // });
//
// fs.readFile('./main.html', function (err, html) {
//
//     if (err) throw err;
//
//     const server = http.createServer(function(request, response) {
//         response.writeHeader(200, {"Content-Type": "application/html"});
//         response.write(html);
//         response.end();
//     }).listen(port, host);
//     console.log(`Server is running on http://${host}:${port}`);
//
// });
// // const http = require('http');
// //
// // const agent = new http.Agent({
// //     rejectUnauthorized: false,
// // });
// //
// // fetch('https://localhost:4000/product/all', {
// //     headers: {
// //         'Accept': 'application/json'
// //     },
// //     body: agent
// // })
// //     .then(response => response.text())
// //     .then(text =>
// //         document.write(`<p>${text}</p>`)
// //     )