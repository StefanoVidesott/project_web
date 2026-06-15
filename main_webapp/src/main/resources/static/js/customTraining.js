let counter = 1;

function addExercise() {
    const container = document.getElementById("exerciseList");
    const originalSelect = document.querySelector('select[name="exercises[0].exerciseName"]');

    const newForm = document.createElement('div');
    newForm.classList.add('blocco-esercizi');
    newForm.style.marginTop = "15px";

    newForm.innerHTML = `
        <hr class="border border-primary border-3 opacity-75">

        <label>Nome esercizio</label>
        <select class="form-select mb-2" aria-label="Default select example" name="exercises[${counter}].exerciseName" required>
            ${originalSelect.innerHTML}
        </select>

        <label>Numero ripetizioni</label>
        <input type="number" name="exercises[${counter}].reps" placeholder="Numero ripetizioni..." required>

        <label>Numero serie</label>
        <input type="number" name="exercises[${counter}].sets" placeholder="Numero serie..." required>
    `;

    container.appendChild(newForm);

    counter++;
}
