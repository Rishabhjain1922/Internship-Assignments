document.addEventListener('DOMContentLoaded', () => {
    if (window.location.pathname.endsWith('employees.html')) {
        checkSession().then(loadEmployees);
    }
});

async function checkSession() {
    try {
        const response = await fetch('/api/hr/check-session', {
            credentials: 'include'
        });

        if (!response.ok) {
            window.location.href = '/index.html';
        }
    } catch (error) {
        console.error('Session check failed:', error);
        window.location.href = '/index.html';
    }
}

// Login Handler
document.getElementById('loginForm')?.addEventListener('submit', async (e) => {
    e.preventDefault();
    try {
        const response = await fetch('/api/hr/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                username: document.getElementById('username').value,
                password: document.getElementById('password').value
            }),
            credentials: 'include'
        });

        if (response.ok) {
            window.location.href = '/employees.html';
        } else {
            alert('Invalid credentials');
        }
    } catch (error) {
        console.error('Login error:', error);
        alert('Login failed');
    }
});

// Employee CRUD Operations
async function loadEmployees() {
    try {
        const response = await fetch('/api/employees', { credentials: 'include' });
        if (!response.ok) throw new Error('Failed to load employees');

        const employees = await response.json();
        const tbody = document.querySelector('#employeeTable');
        tbody.innerHTML = employees.map(emp => `
            <tr>
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.department}</td>
                <td>${emp.email}</td>
                <td>$${emp.salary.toFixed(2)}</td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="viewEmployee(${emp.id})">View</button>
                    <button class="btn btn-sm btn-warning" onclick="openEditModal(${emp.id})">Edit</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteEmployee(${emp.id})">Delete</button>
                </td>
            </tr>
        `).join('');
    } catch (error) {
        console.error('Error:', error);
        alert(error.message);
    }
}

async function saveEmployee() {
    const employee = {
        name: document.getElementById('addName').value,
        department: document.getElementById('addDept').value,
        email: document.getElementById('addEmail').value,
        salary: parseFloat(document.getElementById('addSalary').value)
    };

    try {
        const response = await fetch('/api/employees', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(employee),
            credentials: 'include'
        });

        if (response.ok) {
            $('#addModal').modal('hide');
            document.getElementById('addEmployeeForm').reset();
            await loadEmployees();
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

async function deleteEmployee(id) {
    if (!confirm('Are you sure?')) return;

    try {
        const response = await fetch(`/api/employees/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        });

        if (response.ok) {
            await loadEmployees();
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

async function logout() {
    try {
        await fetch('/api/hr/logout', {
            method: 'POST',
            credentials: 'include'
        });
        window.location.href = '/index.html';
    } catch (error) {
        console.error('Logout error:', error);
    }
}

// Account Creation
document.getElementById('createAccountForm')?.addEventListener('submit', async (e) => {
    e.preventDefault();

    const userData = {
        name: document.getElementById('name').value,
        username: document.getElementById('newUsername').value,
        password: document.getElementById('newPassword').value,
        phoneNumber: document.getElementById('phone').value
    };

    try {
        const response = await fetch('/api/hr/create', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(userData)
        });

        if (response.ok) {
            alert('Account created successfully!');
            window.location.href = '/index.html';
        } else {
            const error = await response.text();
            alert(error || 'Account creation failed');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error creating account');
    }
});


async function viewEmployee(id) {
    try {
        const response = await fetch(`/api/employees/${id}`, { credentials: 'include' });

        if (response.status === 403) {
            alert('Access denied');
            return;
        }
        if (!response.ok) {
            alert('Employee not found');
            return;
        }

        const employee = await response.json();
        document.getElementById('viewId').value = employee.id;
        document.getElementById('viewName').value = employee.name;
        document.getElementById('viewDept').value = employee.department;
        document.getElementById('viewEmail').value = employee.email;
        document.getElementById('viewSalary').value = employee.salary.toFixed(2);
        new bootstrap.Modal(document.getElementById('viewModal')).show();
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to view employee');
    }
}


// Open Edit Modal with Employee Data

async function openEditModal(id) {
    try {
        const response = await fetch(`/api/employees/${id}`, { credentials: 'include' });

        if (response.status === 403) {
            alert('Access denied');
            return;
        }
        if (!response.ok) {
            alert('Employee not found');
            return;
        }

        const employee = await response.json();
        document.getElementById('editId').value = employee.id;
        document.getElementById('editName').value = employee.name;
        document.getElementById('editDept').value = employee.department;
        document.getElementById('editEmail').value = employee.email;
        document.getElementById('editSalary').value = employee.salary;
        new bootstrap.Modal(document.getElementById('editModal')).show();
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to edit employee');
    }
}


// Update Employee
async function updateEmployee() {
    try {
        const id = document.getElementById('editId').value;
        const employeeData = {
            name: document.getElementById('editName').value,
            department: document.getElementById('editDept').value,
            email: document.getElementById('editEmail').value,
            salary: parseFloat(document.getElementById('editSalary').value)
        };

        const response = await fetch(`/api/employees/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(employeeData),
            credentials: 'include'
        });

        if (!response.ok) throw new Error('Update failed');

        await loadEmployees();
        $('#editModal').modal('hide');
    } catch (error) {
        console.error('Error:', error);
        alert(error.message);
    }
}
