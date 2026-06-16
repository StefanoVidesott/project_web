async function loadReviews() {
    try {
        const reviewEndpoint = document.getElementById('reviewEndpoint').textContent.trim();
        const response = await fetch(reviewEndpoint);
        if (!response.ok) {
            throw new Error('Errore HTTP: ' + response.status);
        }
        const reviews = await response.json();

        const container = document.getElementById('reviewsSlidesContainer');
        container.innerHTML = '';

        if (reviews.length === 0) {
            container.innerHTML = `
                <div class="carousel-item active">
                    <div class="p-4 text-center">
                        <p class="text-muted">Nessuna recensione ancora. Sii il primo!</p>
                    </div>
                </div>`;
            return;
        }

        const shuffled = reviews.sort(() => Math.random() - 0.5);

        shuffled.forEach((rec, index) => {
            const slide = document.createElement('div');
            slide.className = 'carousel-item' + (index === 0 ? ' active' : '');
            slide.innerHTML = `
                <div class="p-4 text-center mx-5">
                    <p class="mb-2">"${rec.content}"</p>
                    <small class="text-muted">${rec.username}</small>
                </div>`;
            container.appendChild(slide);
        });

    } catch (error) {
        console.error('Errore nel caricamento delle recensioni:', error);
    }
}

function addCarouselSlide(review) {
    const container = document.getElementById('reviewsSlidesContainer');

    const placeholder = container.querySelector('.carousel-item.active p.text-muted');
    if (placeholder) {
        container.innerHTML = '';
    }

    const slide = document.createElement('div');
    slide.className = 'carousel-item';
    slide.innerHTML = `
        <div class="p-4 text-center mx-5">
            <p class="mb-2">"${review.content}"</p>
            <small class="text-muted">${review.username}</small>
        </div>`;
    container.appendChild(slide);

    if (container.querySelectorAll('.carousel-item').length === 1) {
        slide.classList.add('active');
    }
}

document.addEventListener('DOMContentLoaded', loadReviews);
