// load.js
const { contextBridge } = require('electron');
const path = require('path');
const fs = require('fs');

const dir = path.join(__dirname, 'files');
if (!fs.existsSync(dir)) fs.mkdirSync(dir, { recursive: true });

contextBridge.exposeInMainWorld('electron', {
  getFileNames: () => fs.readdirSync(dir).join('\n'),
  readFile: (name) => fs.readFileSync(path.join(dir, name), 'utf8'),
  saveFile: (name, content) => { fs.writeFileSync(path.join(dir, name), content, 'utf8'); return true; },
  // deleteFile: (name) => { fs.unlinkSync(path.join(dir, name)); return true; }
});
