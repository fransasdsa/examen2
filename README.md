Verifica si ya tienes el repositorio remoto configurado. Usa el siguiente comando para listar los remotos:

bash
Copiar código
git remote -v
Si ves que ya está el remoto origin configurado para https://github.com/fransasdsa/examen2.git, no necesitas agregarlo nuevamente. Si está mal configurado, puedes eliminarlo con el siguiente comando:

bash
Copiar código
git remote remove origin
Agrega el nuevo remoto (si es necesario):

bash
Copiar código
git remote add origin https://github.com/fransasdsa/examen2.git
Agrega los archivos, haz commit y sube los cambios:

bash
Copiar código
git add .
git commit -m "Subiendo proyecto a GitHub"
git push -u origin master
