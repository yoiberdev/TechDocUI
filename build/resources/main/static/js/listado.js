// Smooth scrolling con Lenis
const lenis = new Lenis({
    duration: 1.2,
    easing: (t) => Math.min(1, 1.001 - Math.pow(2, -10 * t)),
    smooth: true
});

function raf(time) {
    lenis.raf(time);
    requestAnimationFrame(raf);
}
requestAnimationFrame(raf);

// Animaciones al cargar
document.addEventListener('DOMContentLoaded', () => {
    const mainTl = gsap.timeline({ defaults: { ease: 'power3.out' } });

    // Ocultar loader
    mainTl.to('.loading-overlay', {
        opacity: 0,
        visibility: 'hidden',
        duration: 0.5,
        onComplete: () => document.querySelector('.loading-overlay')?.remove()
    });

    // Animar main
    mainTl.to('#main-content', {
        opacity: 1,
        y: 0,
        duration: 1
    }, '-=0.2');

    // Fade slide para cabecera
    mainTl.from('[data-gsap="fade-slide"]', {
        opacity: 0,
        x: -30,
        duration: 0.8,
        stagger: 0.1
    }, '-=0.5');

    // Texto principal
    mainTl.from('[data-gsap="text-reveal"]', {
        opacity: 0,
        y: 30,
        duration: 1
    }, '-=0.7');

    // Botones
    mainTl.from('[data-gsap="button"]', {
        opacity: 0,
        scale: 0.8,
        duration: 0.6,
        stagger: 0.1,
        ease: 'back.out(1.7)'
    }, '-=0.5');

    // Filtros
    mainTl.from('[data-gsap="slide-up"]', {
        opacity: 0,
        y: 40,
        duration: 0.8
    }, '-=0.3');

    // Tabla
    mainTl.from('[data-gsap="scale-in"]', {
        opacity: 0,
        scale: 0.95,
        duration: 0.8
    }, '-=0.3');

    // Filas de tabla
    const tableRows = gsap.utils.toArray('.table-row');
    mainTl.from(tableRows, {
        opacity: 0,
        x: -50,
        duration: 0.5,
        stagger: {
            amount: 0.3,
            from: 'start'
        }
    }, '-=0.3');

    // Paginación
    mainTl.from('[data-gsap="fade-bounce"]', {
        opacity: 0,
        y: 20,
        duration: 0.6,
        ease: 'bounce.out'
    }, '-=0.2');

    // Hover botones
    document.querySelectorAll('.btn').forEach(btn => {
        btn.addEventListener('mouseenter', () => {
            gsap.to(btn, { scale: 1.05, duration: 0.3 });
        });
        btn.addEventListener('mouseleave', () => {
            gsap.to(btn, { scale: 1, duration: 0.3 });
        });
    });

    // Click en cabeceras ordenables
    const sortableHeaders = document.querySelectorAll('.sortable');
    sortableHeaders.forEach(header => {
        header.addEventListener('click', function () {
            gsap.to(this, { scale: 0.95, duration: 0.1, yoyo: true, repeat: 1 });
            sortableHeaders.forEach(h => h.classList.remove('sorted-asc', 'sorted-desc'));
            const current = this.dataset.direction || 'ASC';
            const next = current === 'ASC' ? 'DESC' : 'ASC';
            this.dataset.direction = next;
            this.classList.add(`sorted-${next.toLowerCase()}`);
            // Lógica de redirección la defines tú
        });
    });

    // Parallax ScrollTrigger
    gsap.registerPlugin(ScrollTrigger);
    gsap.to('.parallax-wrapper', {
        scrollTrigger: {
            trigger: '.parallax-wrapper',
            start: 'top bottom',
            end: 'bottom top',
            scrub: 1
        },
        y: -50
    });

    // Hover en badges
    document.querySelectorAll('.estado-badge').forEach(badge => {
        badge.addEventListener('mouseenter', () => gsap.to(badge, { scale: 1.1, duration: 0.3 }));
        badge.addEventListener('mouseleave', () => gsap.to(badge, { scale: 1, duration: 0.3 }));
    });

    // Formulario filtros + animación salida
    const filterForm = document.getElementById('filterForm');
    if (filterForm) {
        filterForm.addEventListener('submit', (e) => {
            e.preventDefault();
            gsap.to('#main-content', {
                opacity: 0,
                scale: 0.95,
                duration: 0.3,
                onComplete: () => {
                    const params = new URLSearchParams(new FormData(filterForm));
                    params.set('page', '0');
                    window.location.href = '/sistemas/paginados?' + params.toString();
                }
            });
        });
    }

    // Intersection Observer para fade-in al scroll
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                gsap.to(entry.target, {
                    opacity: 1,
                    y: 0,
                    duration: 0.8
                });
                observer.unobserve(entry.target);
            }
        });
    }, { threshold: 0.1, rootMargin: '0px 0px -50px 0px' });

    document.querySelectorAll('.observe-animate').forEach(el => {
        gsap.set(el, { opacity: 0, y: 30 });
        observer.observe(el);
    });
});

// Transición de página
function pageTransition(href) {
    gsap.to('#main-content', {
        opacity: 0,
        y: -30,
        duration: 0.4,
        onComplete: () => window.location.href = href
    });
}

// Interceptar enlaces
document.addEventListener('click', (e) => {
    const link = e.target.closest('a');
    if (link && link.href && !link.hasAttribute('data-no-transition')) {
        e.preventDefault();
        pageTransition(link.href);
    }
});

// Ripple effect
function createRipple(e) {
    const button = e.currentTarget;
    const ripple = document.createElement('span');
    const rect = button.getBoundingClientRect();
    const size = Math.max(rect.width, rect.height);
    ripple.style.width = ripple.style.height = size + 'px';
    ripple.style.left = e.clientX - rect.left - size / 2 + 'px';
    ripple.style.top = e.clientY - rect.top - size / 2 + 'px';
    ripple.classList.add('ripple');
    button.appendChild(ripple);
    gsap.to(ripple, {
        scale: 2,
        opacity: 0,
        duration: 0.6,
        onComplete: () => ripple.remove()
    });
}

// Aplicar ripple
document.querySelectorAll('.btn').forEach(btn => {
    btn.addEventListener('click', createRipple);
});
