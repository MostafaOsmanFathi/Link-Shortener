const API_BASE = '/links';

function createShortLink() {
    const input = document.getElementById('originalUrl');
    const url = input.value.trim();
    if (!url) {
        alert("Please enter a URL.");
        return;
    }

    fetch(API_BASE + '/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(url)
    })
        .then(res => res.json())
        .then(data => {
            alert("Shortened to: " + data.shortLinkUrl);
            input.value = '';
            loadLinks();
        })
        .catch(err => {
            console.error("Error creating link:", err);
            alert("Failed to create short link.");
        });
}

function loadLinks() {
    fetch(API_BASE + '/')
        .then(res => res.json())
        .then(links => {
            const list = document.getElementById('linkList');
            list.innerHTML = '';

            links.forEach(link => {
                const fullShortUrl = `${window.location.origin}/links/resolve/${link.shortLinkCode}`;
                const item = document.createElement('div');
                item.className = 'link-item';

                item.innerHTML = `
          <div class="link-text">
            <a href="${link.originalUrl}" target="_blank">🔗 ${link.originalUrl}</a>
            <a href="${fullShortUrl}" target="_blank">➡️ ${fullShortUrl}</a>
          </div>
          <button class="delete-btn" onclick="deleteLink('${link.shortLinkCode}')">Delete</button>
        `;

                list.appendChild(item);
            });
        });
}

function deleteLink(code) {
    fetch(`${API_BASE}/${code}`, { method: 'DELETE' })
        .then(() => loadLinks())
        .catch(err => {
            console.error("Error deleting link:", err);
            alert("Failed to delete link.");
        });
}

// Initial load
loadLinks();
