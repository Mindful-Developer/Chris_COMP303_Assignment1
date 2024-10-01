document.addEventListener('DOMContentLoaded', function() {
    const phoneBrandSelect = document.querySelector('#phoneBrand');
    const phoneModelSelect = document.querySelector('#phoneModel');
    const phoneCost = document.querySelector('#phoneCost');

    let phones = {};

    fetch('/api/phones')
        .then(response => response.json())
        .then(data => {
            phones = data;
            updatePhoneBrands();
        })
        .catch(error => {
            console.error('Error fetching phones:', error);
        });

    function updatePhoneBrands() {
        phoneBrandSelect.innerHTML = '<option value="">Select Phone Brand</option>';
        for (let brand of phones.brands) {
            const option = document.createElement('option');
            option.value = brand.name;
            option.innerText = brand.name;
            phoneBrandSelect.appendChild(option);
        }
    }

    function updatePhoneModels() {
        const selectedBrand = phoneBrandSelect.value;
        phoneModelSelect.innerHTML = '<option value="">Select Phone Model</option>';
        if (selectedBrand) {
            const phoneBrand = phones.brands.find(brand => brand.name === selectedBrand);
            for (let model of phoneBrand.models) {
                const option = document.createElement('option');
                option.value = model.name;
                option.innerText = model.name
                phoneModelSelect.appendChild(option);
            }
        }
        updatePhoneCost();
        phoneModelSelect.addEventListener('change', updatePhoneCost);
    }

    function updatePhoneCost() {
        const selectedBrand = phoneBrandSelect.value;
        const selectedModel = phoneModelSelect.value;

        if (selectedBrand && selectedModel) {
            for (let brand of phones.brands) {
                if (brand.name === selectedBrand) {
                    for (let model of brand.models) {
                        if (model.name === selectedModel) {
                            phoneCost.value = '$' + model.price + '.00';
                        }
                    }
                    return;
                }
            }
        }
        phoneCost.innerText = '$0.00';
    }

    phoneBrandSelect.addEventListener('change', updatePhoneModels);
    updatePhoneModels();
});