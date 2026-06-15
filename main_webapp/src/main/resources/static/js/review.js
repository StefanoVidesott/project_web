async function sendReview() {
    const content = document.getElementById('reviewContent').value.trim();
    const msgDiv = document.getElementById('reviewMsg');
    msgDiv.innerHTML = '';

    if (content === '') {
        msgDiv.innerHTML = '<div class="alert alert-warning">Scrivi qualcosa prima di inviare.</div>';
        return;
    }

    try {
        const formData = new FormData();
        formData.append('content', content);

        const response = await fetch('/reviews', {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error('Errore HTTP: ' + response.status);
        }

        const newReview = await response.json();

        addCarouselSlide(newReview);

        document.getElementById('reviewContent').value = '';
        msgDiv.innerHTML = '<div class="alert alert-success">Recensione inserita con successo!</div>';

    } catch (error) {
        console.error('Errore:', error);
        msgDiv.innerHTML = '<div class="alert alert-danger">Errore durante l\'invio.</div>';
    }
}
