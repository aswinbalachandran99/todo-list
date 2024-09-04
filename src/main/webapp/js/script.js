function editTodo(id, task, dueDate, completed) {
    document.getElementById('editId').value = id;
    document.getElementById('editTask').value = task;
    document.getElementById('editDueDate').value = dueDate;
    document.getElementById('editCompleted').checked = completed;
    document.getElementById('editModal').style.display = 'block';
}

function closeEditModal() {
    document.getElementById('editModal').style.display = 'none';
}

// Close the modal if the user clicks outside of it
window.onclick = function(event) {
    var modal = document.getElementById('editModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
