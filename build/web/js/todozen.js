// Dark/Light mode toggle
function toggleDarkMode() {
    document.body.classList.toggle('dark-mode');
    localStorage.setItem('darkMode', document.body.classList.contains('dark-mode'));
}

window.onload = function() {
    if (localStorage.getItem('darkMode') === 'true') {
        document.body.classList.add('dark-mode');
    }
    // Add dark mode toggle button
    const nav = document.querySelector('nav .container-fluid');
    if (nav) {
        const btn = document.createElement('button');
        btn.className = 'btn btn-outline-light ms-2';
        btn.innerText = 'Toggle Dark Mode';
        btn.onclick = toggleDarkMode;
        nav.appendChild(btn);
    }
};
