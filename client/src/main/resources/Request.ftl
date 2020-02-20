<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:orb="http://orbcit.com">
    <soapenv:Header/>
    <soapenv:Body>
        <orb:GetBankRequest>
            <orb:id>${id}</orb:id>
        </orb:GetBankRequest>
    </soapenv:Body>
</soapenv:Envelope>